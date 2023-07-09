SUMMARY = "Release image"

inherit image

IMAGE_FEATURES += "package-management"
IMAGE_LINGUAS = "en-us"


CORE_OS = "\
    openssh openssh-keygen openssh-sftp-server \
    packagegroup-core-boot \
    tzdata \
    kernel-modules \
"

EXTRA_TOOLS = " \
    android-tools \
    can-utils \
    coreutils \
    curl \
    diffutils \
    dosfstools \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    e2fsprogs-resize2fs \
    parted \
    ethtool \
    findutils \
    i2c-tools \
    ifupdown \
    iperf3 \
    iproute2 \
    iptables \
    lsof \
    ntp ntp-tickadj \
    procps \
    sysfsutils \
    tcpdump \
    util-linux \
    util-linux-blkid \
    unzip \
    wget \
    zip \
"

WIFI = " \
    crda \
    iw \
    bcm43xx-utils \
    bluez5 \
    bluez-alsa \
    brcm-patchram-plus \
    wpa-supplicant \
    wlconf \
    hostapd \
    linux-firmware-bcm4339 \
    linux-firmware-bcm43430 \
"

USER_SOFTWARE = " \
    user-scripts \
    user-binaries \
"

USER_CONFIG = " \
    config-files \
    user-services \
"

IMAGE_INSTALL += " \
    ${CORE_OS} \
    ${EXTRA_TOOLS} \
    ${WIFI} \
    ${USER_CONFIG} \
    ${USER_SOFTWARE} \
"

WKS_FILE = "release-wic.wks"

inherit extrausers
EXTRA_USERS_PARAMS = "\
			useradd -P passwd1 user1; \
			useradd -P passwd2 user2; \
			"
         
mount_smackfs () {

    cat >> ${IMAGE_ROOTFS}/etc/fstab <<EOF
    
/dev/mmcblk1p1       /boot           vfat    defaults        1       2
/dev/mmcblk1p2       /               ext4    defaults        1       1
/dev/mmcblk1p3       /home           ext4    defaults        0       0 

EOF
} 
ROOTFS_POSTPROCESS_COMMAND += "mount_smackfs; "

export IMAGE_BASENAME = "release-image"


