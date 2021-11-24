DESCRIPTION = "Scripts"
SECTION = "Scripts de usuario"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SRC_URI = "file://userinit.sh "

S = "${WORKDIR}"

do_install() {
	install -d ${D}${sbindir}
	install -m 755 userinit.sh ${D}${sbindir}
}

FILES_${PN} += "${sbindir}"


