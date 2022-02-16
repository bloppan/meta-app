FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-enable-CAN-disable-UART3.patch \
            file://0002-Add-sd-card-external-and-internal-device-tree.patch \
            file://defconfig \
            "

