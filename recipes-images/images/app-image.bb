SUMMARY = "Simple app image"

inherit image

IMAGE_FEATURES += "package-management"
IMAGE_LINGUAS = "en-us"


CORE_OS = "\
    openssh openssh-keygen openssh-sftp-server \
    packagegroup-core-boot \
    tzdata \
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
    bzip2 \
    coreutils \
    curl \
    diffutils \
    dosfstools \
    e2fsprogs-mke2fs \
    ethtool \
    fbset \
    findutils \
    grep \
    i2c-tools \
    ifupdown \
    iperf3 \
    iproute2 \
    iptables \
    less \
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
    wpa-supplicant \
"


USER_TOOLS = " \
    user-apps \
    user-tests \
    user-libs \
    user-scripts \
    "
    
IMAGE_INSTALL += " \
    ${CORE_OS} \
    ${DEV_SDK} \
    ${EXTRA_TOOLS} \
    ${WIFI} \
    ${USER_TOOLS} \
    config-files \
    init-services \
"

inherit extrausers
EXTRA_USERS_PARAMS = "\
			useradd -P passwd1 bernar; \
			useradd -P passwd2 bernux; \
			"
         
mount_smackfs () {

    cat >> ${IMAGE_ROOTFS}/etc/fstab <<EOF
    
/dev/mmcblk0p1       /boot           vfat    defaults        1       2
/dev/mmcblk0p2       /               ext4    defaults        1       1
/dev/mmcblk0p3       /home           ext4    defaults        0       0 

EOF
} 
ROOTFS_POSTPROCESS_COMMAND += "mount_smackfs; "

export IMAGE_BASENAME = "app-image"


