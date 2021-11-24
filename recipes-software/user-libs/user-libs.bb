DESCRIPTION = "Shared libraries for user app"
SECTION = "Libraries"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://app_shared_libraries/LICENSE;md5=7220a67438d478d0984aee2965bffc11"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCREV = "${AUTOREV}"
SRCREV_FORMAT = "none"

SRC_URI = " \
	git://github.com/blpanadero/app_shared_libraries.git;destsuffix=git/app_shared_libraries \
	git://github.com/blpanadero/app_includes.git;destsuffix=git/app_includes"

S = "${WORKDIR}/git"

PV = "1"

#INSANE_SKIP_${PN} = "ldflags"
FILES_${PN} = "${libdir} "

do_compile() {

	${CC} ${CFLAGS} ${LDFLAGS} -shared -fPIC -Wl,-soname,libGPIO.so.${PV} ${S}/app_shared_libraries/libGPIO/GPIO.c -o libGPIO.so.${PV}
	${CC} ${CFLAGS} ${LDFLAGS} -shared -fPIC -Wl,-soname,libI2C.so.${PV} ${S}/app_shared_libraries/libI2C/I2C.c -o libI2C.so.${PV}
	${CC} ${CFLAGS} ${LDFLAGS} -shared -fPIC -Wl,-soname,libHTS221.so.${PV} ${S}/app_shared_libraries/libHTS221/HTS221.c -o libHTS221.so.${PV}
	${CC} ${CFLAGS} ${LDFLAGS} -shared -fPIC -Wl,-soname,libPAC1932.so.${PV} ${S}/app_shared_libraries/libPAC1932/PAC1932.c -o libPAC1932.so.${PV}
	${CC} ${CFLAGS} ${LDFLAGS} -shared -fPIC -Wl,-soname,libPCA9532.so.${PV} ${S}/app_shared_libraries/libPCA9532/PCA9532.c -o libPCA9532.so.${PV}
}

do_install() {

	install -d ${D}${libdir}
	install -m 0755 ${S}/lib*.so* ${D}${libdir}
}





