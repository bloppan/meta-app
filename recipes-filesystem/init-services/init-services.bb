DESCRIPTION = "User Init Service"
SRC_URI = "file://userinit-service"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME = "userinit-service"

do_install() {
	install -d ${D}${INIT_D_DIR}
	install -m 0755 ${WORKDIR}/userinit-service ${D}${INIT_D_DIR}/userinit-service
}

# package it as it is not installed in a standard location*
FILES_${PN} += "${INIT_D_DIR}"
