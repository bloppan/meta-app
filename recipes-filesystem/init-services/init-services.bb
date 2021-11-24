DESCRIPTION = "User Init Service"
SRC_URI = "file://userinit"
LICENSE = "MIT"
LIC_FILES_CHKSUM =""
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# these 3 lines will have the script run on boot
inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME = "userinit"

# install it in the correct location for update-rc.d

do_install() {
	install -d ${D}${INIT_D_DIR}
	install -m 0755 ${WORKDIR}/userinit ${D}${INIT_D_DIR}/userinit
}

# package it as it is not installed in a standard location*
FILES_${PN} += "${INIT_D_DIR}"
