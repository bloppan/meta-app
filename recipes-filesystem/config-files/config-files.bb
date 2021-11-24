DESCRIPTION = "Dummy conf filie"
SECTION = "Configuracion de usuario"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SRC_URI = "file://dummy_file.conf "

S = "${WORKDIR}"

do_install() {
	install -d ${D}${sysconfdir}
	install -m 755 dummy_file.conf ${D}${sysconfdir}
}

FILES_${PN} += "${sysconfdir}"


