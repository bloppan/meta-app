DESCRIPTION = "Test user"
SECTION = "User Applications"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCREV = "${AUTOREV}"
SRCREV_FORMAT = "none"

SRC_URI = " \
		git://github.com/blpanadero/app_shared_libraries.git;destsuffix=git/app_shared_libraries \
		git://github.com/blpanadero/app_tests.git;destsuffix=git/app_tests \
		git://github.com/blpanadero/app_includes.git;destsuffix=git/app_includes "

S = "${WORKDIR}/git"

#INSANE_SKIP_${PN} = "ldflags"
FILES_${PN} = "${bindir} "

do_compile() {
	${CC} ${CFLAGS} ${LDFLAGS} -shared app_tests/GPIO_test/main.c -o GPIO_test
	${CC} ${CFLAGS} ${LDFLAGS} -shared app_tests/HTS221_test/main.c -o HTS221_test
	${CC} ${CFLAGS} ${LDFLAGS} -shared app_tests/PAC1932_test/main.c -o PAC1932_test
	${CC} ${CFLAGS} ${LDFLAGS} -shared app_tests/PCA9532_test/main.c -o PCA9532_test
}

do_install() {

	install -d ${D}${bindir}
	install -m 0755 ${S}/GPIO_test ${D}${bindir}
	install -m 0755 ${S}/HTS221_test ${D}${bindir}
	install -m 0755 ${S}/PAC1932_test ${D}${bindir}
	install -m 0755 ${S}/PCA9532_test ${D}${bindir}
}


