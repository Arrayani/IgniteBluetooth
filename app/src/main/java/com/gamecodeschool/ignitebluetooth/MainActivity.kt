package com.gamecodeschool.ignitebluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.getSystemService
import com.gamecodeschool.ignitebluetooth.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    companion object {
        private const val BLUETOOTH_PERMISSION_REQUEST_CODE = 9999
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bluetoothManager=applicationContext.getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
        val bluetoothAdapter=bluetoothManager.adapter
        binding.btnBluetoothOn.setOnClickListener{
            val intent= Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(intent,1)
            Toast.makeText(this,"Bluetooth Enable",Toast.LENGTH_SHORT).show()
        }
        binding.btnBluetoothOff.setOnClickListener{
            bluetoothAdapter.disable()
            Toast.makeText(this,"Bluetooth Disable",Toast.LENGTH_SHORT).show()
        }

    }
    private var requestBluetoothEnable =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            Log.d(TAG, "IT RESULT CODE: ${it.resultCode.toString()}")
            //kiedy bt jest wlaczone , result -1 , kiedy wylaczone i wlaczamy i akceptujemy tez -1
            //a jak odrzucamy to 0
            if (it.resultCode == -1) {
                conditions.log()
                conditions.isBtEnabled = true
            }
            if (conditions.isReady()) {
                buildInterfaceOk()
            } else buildInterfaceError()
        }
}