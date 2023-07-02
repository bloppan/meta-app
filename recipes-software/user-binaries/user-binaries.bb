DESCRIPTION = "User binaries"
SECTION = "Applications and libraries"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCREV = "${AUTOREV}"
SRCREV_FORMAT = "none"

SRC_URI = "git://github.com/bloppan/linux_userspace.git;destsuffix=git/linux_userspace;protocol=https;branch=main "

S = "${WORKDIR}/git"
PV = "1"

FILES_${PN} = "${libdir} "

do_compile() {

	${CC} ${CFLAGS} ${LDFLAGS} -shared -fPIC -Wl,-soname,libGPIO.so.${PV} ${S}/linux_userspace/libGPIO/GPIO.c -o libGPIO.so.${PV}
	${CC} ${CFLAGS} ${LDFLAGS} -shared -fPIC -Wl,-soname,libI2C.so.${PV} ${S}/linux_userspace/libI2C/I2C.c -o libI2C.so.${PV}
	${CC} ${CFLAGS} ${LDFLAGS} -shared -fPIC -Wl,-soname,libPCA9532.so.${PV} ${S}/linux_userspace/libPCA9532/PCA9532.c -o libPCA9532.so.${PV}
#	${CC} ${CFLAGS} ${LDFLAGS} linux_userspace/PCA9532_test/main.c -o PCA9532_test -Wl,--no-as-needed -ldl
}

do_install() {

#	install -d ${D}${libdir}
#	install -m 0755 ${S}/PCA9532_test ${D}${bindir}
	install -d ${D}${libdir}
	install -m 0755 ${S}/lib*.so* ${D}${libdir}
}
