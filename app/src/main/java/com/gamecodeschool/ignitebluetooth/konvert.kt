package com.gamecodeschool.ignitebluetooth

/**

 * Author: Roni Reynal Fitri  on $ {DATE} $ {HOUR}: $ {MINUTE}

 * Email: biruprinting@gmail.com

 */
class konvert : Activity() {
    companion object {
        private const val BLUETOOTH_PERMISSION_REQUEST_CODE = 9999
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBluetoothOrRequestPermission()

        ...
    }

    private fun initializeBluetoothOrRequestPermission() {
        val requiredPermissions = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            listOf(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            listOf(Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_SCAN)
        }

        val missingPermissions = requiredPermissions.filter { permission ->
            checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED
        }
        if (missingPermissions.isEmpty()) {
            initializeBluetooth()
        } else {
            requestPermissions(missingPermissions.toTypedArray(), BLUETOOTH_PERMISSION_REQUEST_CODE)
        }
    }

    private fun initializeBluetooth() { ... }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            BLUETOOTH_PERMISSION_REQUEST_CODE -> {
                if (grantResults.none { it != PackageManager.PERMISSION_GRANTED }) {
                    // all permissions are granted
                    initializeBluetooth()
                } else {
                    // some permissions are not granted
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    ...
}