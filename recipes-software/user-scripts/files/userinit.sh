#!/bin/sh

set -e

enable_sd_or_wifi() {

	if grep "external" /boot/uEnv.txt; then     
		
		#Multiplexa a la tarjeta SD externa                      
		echo 119 > /sys/class/gpio/export   
		echo out > /sys/class/gpio/gpio119/direction
		echo 0 > /sys/class/gpio/gpio119/value      
		                                            
		echo 118 > /sys/class/gpio/export           
		echo out > /sys/class/gpio/gpio118/direction
		echo 1 > /sys/class/gpio/gpio118/value      
		                                            
	elif grep "internal" /boot/uEnv.txt; then             
		
		#Multiplexa a la tarjeta SD interna                                 
		echo 119 > /sys/class/gpio/export           
		echo out > /sys/class/gpio/gpio119/direction
		echo 0 > /sys/class/gpio/gpio119/value      
		                                            
		echo 118 > /sys/class/gpio/export           
		echo out > /sys/class/gpio/gpio118/direction
		echo 0 > /sys/class/gpio/gpio118/value  

	elif grep "wifi" /boot/uEnv.txt; then

		#Crea el punto de acceso Wifi
		echo 1 > /proc/sys/net/ipv4/ip_forward
		ifconfig wlan0 192.168.5.1
		hostapd -B /etc/hostapd.conf -P /var/run/hostapd.pid
		udhcpd /etc/udhcpd.conf
		iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE
	fi
}

check_swap_file() {

	#Comprueba si se esta arrancando desde la eMMC
	if mount | grep "/dev/mmcblk1"; then
		#Comprueba si existe el fichero swap
		if [ -f /home/swapfile.swap ]; then
			echo "Fichero swapfile.swap encontrado"
		else
			echo "Creando fichero swap..."
			dd if=/dev/zero of=/home/swapfile.swap bs=1K count=2M
			chmod 600 /home/swapfile.swap
			mkswap /home/swapfile.swap
			swapon /home/swapfile.swap
			echo '/home/swapfile.swap swap swap defaults 0 0' | tee -a /etc/fstab
		fi
	fi
}

check_partition3_size() {

	CURRENTSIZEB=`fdisk -l /dev/mmcblk1p3 | grep "Disk /dev/mmcblk1p3" | cut -d' ' -f5`
	CURRENTSIZE=`expr $CURRENTSIZEB / 1024 / 1024`

	#Comprueba si el tama�ño de la particion 3 es menor que 100MB
	if [ "$CURRENTSIZE" -lt 100 ]; then

		#Comprueba si p3 esta montada
		if mount | grep "/dev/mmcblk1p3"; then

		        #Desmonta la particion
			umount -l /dev/mmcblk1p3
		fi

		#Redimensiona la particion /dev/mmcblk1p3
		parted -s /dev/mmcblk1 resizepart 3 100%
		partprobe /dev/mmcblk1
		e2fsck -f -y /dev/mmcblk1p3
		resize2fs /dev/mmcblk1p3
		e2fsck -f -y /dev/mmcblk1p3

		#Si se esta arrancando desde la eMMC, se monta p3
		if mount | grep "/dev/mmcblk1"; then

		        mount /dev/mmcblk1p3 /home/
		fi
	fi
}

check_uEnv_file() {

	#Comprueba si existe el fichero de configuracion del U-Boot (uEnv.txt)
	if ! [ -f /boot/uEnv.txt ]; then

		echo "uEnv.txt not found"
		#Si no existe, posiblemente sea el primer arranque desde que se ha grabado la imagen en la memoria

		#Crea el fichero uEnv.txt
		touch /boot/uEnv.txt

		if grep "SD" /sys/devices/soc0/machine; then
			echo "fdt_file=imx6ull-var-dart-6ulcustomboard-emmc-sd-card-external.dtb" > /boot/uEnv.txt
		else
			echo "fdt_file=imx6ull-var-dart-6ulcustomboard-emmc-wifi.dtb" > /boot/uEnv.txt
		fi
	fi
}

configure_eth_interface() {

	#Configura la interfaz ethernet
	ifconfig eth1 192.168.1.85 up
	route add -net 192.168.1.0 netmask 255.255.255.0 dev eth1
}


support_system_init() {

	check_partition3_size
	check_uEnv_file
	check_swap_file
	
	configure_eth_interface
	enable_sd_or_wifi

	#Lanza la aplicacion principal del sistema de soporte
	#app_daemon &
}


###################################
########## SCRIPT INIT ############
###################################

case "$1" in
    start)
	support_system_init
     ;;
     stop)
	echo " "
	echo "Rebooting support system..."
     ;;
esac

exit 0





                                       





