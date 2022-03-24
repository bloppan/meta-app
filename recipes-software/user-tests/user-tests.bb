DESCRIPTION = "Test user"
SECTION = "Test user applications"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCREV = "${AUTOREV}"
SRCREV_FORMAT = "none"

SRC_URI = " \
		git://github.com/bloppan/app_shared_libraries.git;destsuffix=git/app_shared_libraries;protocol=https \
		git://github.com/bloppan/app_tests.git;destsuffix=git/app_tests;protocol=https \
		git://github.com/bloppan/app_includes.git;destsuffix=git/app_includes;protocol=https "

S = "${WORKDIR}/git"

#INSANE_SKIP_${PN} = "ldflags"
FILES_${PN} = "${bindir} "

do_compile() {
	${CC} ${CFLAGS} ${LDFLAGS} app_tests/GPIO_test/main.c -o GPIO_test -Wl,--no-as-needed -ldl
	${CC} ${CFLAGS} ${LDFLAGS} app_tests/WSEN_TIDS_test/main.c -o WSEN_TIDS_test -Wl,--no-as-needed -ldl
	${CC} ${CFLAGS} ${LDFLAGS} app_tests/PAC1932_test/main.c -o PAC1932_test -Wl,--no-as-needed -ldl
	${CC} ${CFLAGS} ${LDFLAGS} app_tests/PCA9532_test/main.c -o PCA9532_test -Wl,--no-as-needed -ldl
	${CC} ${CFLAGS} ${LDFLAGS} app_tests/CAN_test/main.c -o CAN_test -Wl,--no-as-needed -ldl
}

do_install() {

	install -d ${D}${bindir}
	install -m 0755 ${S}/GPIO_test ${D}${bindir}
	install -m 0755 ${S}/WSEN_TIDS_test ${D}${bindir}
	install -m 0755 ${S}/PAC1932_test ${D}${bindir}
	install -m 0755 ${S}/PCA9532_test ${D}${bindir}
	install -m 0755 ${S}/CAN_test ${D}${bindir}
}


