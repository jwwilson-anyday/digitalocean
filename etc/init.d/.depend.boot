TARGETS = console-setup mountkernfs.sh resolvconf ufw plymouth-log screen-cleanup apparmor hostname.sh udev keyboard-setup cryptdisks cryptdisks-early hwclock.sh mountdevsubfs.sh checkroot.sh open-iscsi networking iscsid urandom lvm2 checkfs.sh procps mountall.sh checkroot-bootclean.sh kmod bootmisc.sh mountall-bootclean.sh mountnfs-bootclean.sh mountnfs.sh
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
hwclock.sh: mountdevsubfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
open-iscsi: networking iscsid
networking: mountkernfs.sh urandom resolvconf procps
iscsid: networking
urandom: hwclock.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
checkfs.sh: cryptdisks lvm2 checkroot.sh
procps: mountkernfs.sh udev
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
checkroot-bootclean.sh: checkroot.sh
kmod: checkroot.sh
bootmisc.sh: checkroot-bootclean.sh mountall-bootclean.sh mountnfs-bootclean.sh udev
mountall-bootclean.sh: mountall.sh
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
