DESCRIPTION = "Apps user"
SECTION = "User Applications"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://app_daemon/LICENSE;md5=7220a67438d478d0984aee2965bffc11"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCREV = "${AUTOREV}"
SRCREV_FORMAT = "none"

SRC_URI = " \
		git://github.com/blpanadero/app_daemon.git;destsuffix=git/app_daemon \
		git://github.com/blpanadero/app_includes.git;destsuffix=git/app_includes"

S = "${WORKDIR}/git"

INSANE_SKIP_${PN} = "ldflags"
FILES_${PN} = "${bindir} "

#do_compile() {
#	${CC} ${CFLAGS} ${LDFLAGS} -lpthread ${S}/app_daemon/main.cpp -o app_daemon
#}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${S}/app_daemon/Release/app_daemon ${D}${bindir}
}


