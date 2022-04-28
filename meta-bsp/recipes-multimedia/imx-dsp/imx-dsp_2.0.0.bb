# Copyright 2017-2022 NXP

DESCRIPTION = "i.MX DSP Wrapper, Firmware Binary, Codec Libraries"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=be5ff43682ed6c57dfcbeb97651c2829"


inherit fsl-eula-unpack autotools pkgconfig

SRC_URI = "${FSL_MIRROR}/${BP}.bin;fsl-eula=true"

SRC_URI[md5sum] = "4d710c08f9b2787b83a175df45ba0640"
SRC_URI[sha256sum] = "d15f109abd0f51fe908c01b9a58ecc69a112cb15cb4bea565e137dcdb88ac68e"

EXTRA_OECONF = "-datadir=${base_libdir}/firmware --bindir=/unit_tests ${@bb.utils.contains('TUNE_FEATURES', 'aarch64', '--enable-armv8', ' ', d)}"

RDEPENDS:${PN} += " imx-dsp-codec-ext"

FILES:${PN} = "${libdir}/imx-mm/audio-codec/dsp \
               ${libdir}/imx-mm/audio-codec/wrap \
               ${base_libdir}/firmware/imx/dsp \
               /unit_tests \
"

HIFI4_BIN ?= "hifi4_imx8qmqxp.bin"
HIFI4_BIN:mx8qm-nxp-bsp  = "hifi4_imx8qmqxp.bin"
HIFI4_BIN:mx8qxp-nxp-bsp = "hifi4_imx8qmqxp.bin"
HIFI4_BIN:mx8dx-nxp-bsp  = "hifi4_imx8qmqxp.bin"
HIFI4_BIN:mx8mp-nxp-bsp  = "hifi4_imx8mp.bin"
HIFI4_BIN:mx8ulp-nxp-bsp = "hifi4_imx8ulp.bin"

do_install:append () {
    # Install different DSP Firmware according to the platform
    if [ -f ${D}/lib/firmware/imx/dsp/${HIFI4_BIN} ]; then
        # Rename DSP Firmware into hifi4.bin and remove unneeded binary
        echo "---Rename ${D}/lib/firmware/imx/dsp/${HIFI4_BIN} into ${D}/lib/firmware/imx/dsp/hifi4.bin---"
        mv ${D}/lib/firmware/imx/dsp/${HIFI4_BIN} ${D}/lib/firmware/imx/dsp/hifi4.bin
        find ${D}/lib/firmware/imx/dsp -name hifi4_*.bin -exec rm {} \;
    fi
}

INSANE_SKIP:${PN} = "already-stripped arch ldflags dev-so"

# Fix strip command failed: 'Unable to recognise the format of the input file'
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(mx8qm-nxp-bsp|mx8qxp-nxp-bsp|mx8dx-nxp-bsp|mx8mp-nxp-bsp|mx8ulp-nxp-bsp)"
