#!/bin/sh


ifconfig eth1 192.168.1.85 up
route add -net 192.168.1.0 netmask 255.255.255.0 dev eth1

echo 137 > /sys/class/gpio/export
echo out > /sys/class/gpio/gpio137/direction
echo 1 > /sys/class/gpio/gpio137/value

echo 4 > /sys/class/gpio/export
echo out > /sys/class/gpio/gpio4/direction
echo 1 > /sys/class/gpio/gpio4/value

echo 119 > /sys/class/gpio/export 
echo out > /sys/class/gpio/gpio119/direction 
echo 0 > /sys/class/gpio/gpio119/value 

echo 118 > /sys/class/gpio/export 
echo out > /sys/class/gpio/gpio118/direction 
echo 0 > /sys/class/gpio/gpio118/value

while true;
do
	sleep 1
done
