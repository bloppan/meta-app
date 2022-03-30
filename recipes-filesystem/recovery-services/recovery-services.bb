DESCRIPTION = "User Init Service"
SRC_URI = "file://recovery-init-service"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME = "recovery-init-service"

do_install() {
	install -d ${D}${INIT_D_DIR}
	install -m 0755 ${WORKDIR}/recovery-init-service ${D}${INIT_D_DIR}/recovery-init-service
}

# package it as it is not installed in a standard location*
FILES_${PN} += "${INIT_D_DIR}"
