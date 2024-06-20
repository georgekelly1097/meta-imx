DESCRIPTION = "A library to retrieve i.MX GPU performance data"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=10c0fda810c63b052409b15a5445671a" 

IMX_SRCREV_ABBREV = "99ab423"

SRC_URI[arm-fb.md5sum] = "5476e6398b1b9068ebe4c4fdfdf19d1e"
SRC_URI[arm-fb.sha256sum] = "81151261b2e2ff63a8d73fdb9b50dda0318a4238df60e29ab577dbbe71701efa"

SRC_URI[arm-wayland.md5sum] = "a7275b7a13d318e5c4b66ca0e9602e38"
SRC_URI[arm-wayland.sha256sum] = "b9409764d54caff5c4ae9075ad0fc42f8c434b17d8d110f84e95b52dbfd43fb9"

SRC_URI[aarch64-wayland.md5sum] = "21479d4bb6f9f76b5cd8a88066f0eb38"
SRC_URI[aarch64-wayland.sha256sum] = "a9a9fb1a25e97bedff6c23e8df6b86241ec390dd44c7dc431f047ecd5e787498"

SRC_URI[aarch64-wayland-mali.md5sum] = "94680287f781520dac39bf0a8ddd972f"
SRC_URI[aarch64-wayland-mali.sha256sum] = "da49c2c62538dd6772e940946af6c3f146a8eeb9d1a8cf9447e7e98bf416cca4"

inherit fsl-eula-unpack2 fsl-eula-graphics fsl-eula-recent

PACKAGECONFIG ??= "vivante"
PACKAGECONFIG:mx95-nxp-bsp = "mali"

PACKAGECONFIG[mali] = ",,,mali-imx,,vivante"
PACKAGECONFIG[vivante] = ",,,imx-gpu-viv,,mali"

PACKAGE_ARCH = "${MACHINE_SOCARCH}"

# Compatible only with i.MX with GPU
COMPATIBLE_MACHINE        = "(^$)"
COMPATIBLE_MACHINE:imxgpu = "${MACHINE}"