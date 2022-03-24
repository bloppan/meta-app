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

INSANE_SKIP_${PN} = "ldflags"
FILES_${PN} = "${bindir} "

do_compile() {
	${CC} ${CFLAGS} ${LDFLAGS} -lpthread -shared ${S}/app_daemon/src/main.cpp -o app_daemon/Release/app_daemon
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${S}/app_daemon/Release/app_daemon ${D}${bindir}
}


