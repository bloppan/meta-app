SUMMARY = "Simple recovery image"

inherit image

IMAGE_FEATURES += "package-management"
IMAGE_LINGUAS = "en-us"


CORE_OS = "\
    openssh openssh-keygen openssh-sftp-server \
    packagegroup-core-boot \
    tzdata \
    kernel-modules \
"

DEV_SDK = " \
    binutils \
    binutils-symlinks \
    coreutils \
    cpp \
    cpp-symlinks \
    diffutils \
    elfutils elfutils-binutils \
    file \
    gcc \
    gcc-symlinks \
    g++ \
    g++-symlinks \
    gettext \
    git \
    ldd \
    libstdc++ \
    libstdc++-dev \
    libtool \
    ltrace \
    make \
    perl-modules \
    pkgconfig \
    python3-modules \
    strace \
"

EXTRA_TOOLS = " \
    android-tools \
    apache2 \
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
    user-apps \
    user-libs \
    user-tests \
    user-scripts \
"

USER_CONFIG = " \
    config-files \
    recovery-services \
"

IMAGE_INSTALL += " \
    ${CORE_OS} \
    ${DEV_SDK} \
    ${EXTRA_TOOLS} \
    ${WIFI} \
    ${USER_CONFIG} \
    ${USER_SOFTWARE} \
"

WKS_FILE = "recovery-wic.wks"

inherit extrausers
EXTRA_USERS_PARAMS = "\
			useradd -P passwd1 user1; \
			useradd -P passwd2 user2; \
			"
         
mount_smackfs () {

    cat >> ${IMAGE_ROOTFS}/etc/fstab <<EOF
    
/dev/mmcblk0p1       /boot           vfat    defaults        1       2
/dev/mmcblk0p2       /               ext4    defaults        1       1
/dev/mmcblk0p3       /home           vfat    defaults        1       2 

EOF
} 
ROOTFS_POSTPROCESS_COMMAND += "mount_smackfs; "

export IMAGE_BASENAME = "recovery-image"


