DESCRIPTION = "Apps user"
SECTION = "User Applications"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCREV = "${AUTOREV}"
SRCREV_FORMAT = "none"

SRC_URI = " \
		git://github.com/bloppan/app_daemon.git;destsuffix=git/app_daemon;protocol=https \
		git://github.com/bloppan/app_includes.git;destsuffix=git/app_includes;protocol=https"

S = "${WORKDIR}/git"

FILES_${PN} = "${bindir} "

do_compile() {

	${CXX} ${CFLAGS} ${LDFLAGS} ${S}/app_daemon/src/*.cpp -o app_daemond -lpthread -ldl
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${S}/app_daemond ${D}${bindir}
}


