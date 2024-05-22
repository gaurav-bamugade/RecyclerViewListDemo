package com.example.recyclerviewlistdemo.zebra

import android.content.Context
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.util.Log
import android.widget.Toast
import com.zebra.sdk.comm.Connection
import com.zebra.sdk.comm.ConnectionException
import com.zebra.sdk.comm.UsbConnection
import com.zebra.sdk.printer.PrinterStatus
import com.zebra.sdk.printer.ZebraPrinter
import com.zebra.sdk.printer.ZebraPrinterFactory
import com.zebra.sdk.printer.ZebraPrinterLanguageUnknownException
import java.io.File
import java.io.FileInputStream

class  ZebraPrinterHandler(private val context: Context) {

    private val usbManager: UsbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager

    fun findUsbDevice(): UsbDevice? {

        val usbDevices = usbManager.deviceList
        for (device in usbDevices.values) {
            // Adjust this logic to match your printer's vendor ID and product ID
            if (device.vendorId == 0x0A5F && device.productId == 0x007E) {
                return device
            }
        }
        return null
    }

    fun sendPrnFileToPrinter(usbDevice: UsbDevice, filePath: String) {
        Thread {
            var connection: Connection? = null
            try {
                connection = UsbConnection(context, usbDevice)
                connection.open()

                val printer = ZebraPrinterFactory.getInstance(connection)
                val printerStatus = printer.currentStatus

                if (printerStatus.isReadyToPrint) {
                    val file = File(filePath)
                    val fileInputStream = FileInputStream(file)
                    val fileData = ByteArray(file.length().toInt())
                    fileInputStream.read(fileData)
                    fileInputStream.close()

                    connection.write(fileData)
                    (context as Activity).runOnUiThread {
                        Toast.makeText(context, "Print successful", Toast.LENGTH_LONG).show()
                    }
                } else {
                    (context as Activity).runOnUiThread {
                        Toast.makeText(context, "Printer is not ready to print", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: ConnectionException) {
                e.printStackTrace()
                (context as Activity).runOnUiThread {
                    Toast.makeText(context, "Connection error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            } catch (e: ZebraPrinterLanguageUnknownException) {
                e.printStackTrace()
                (context as Activity).runOnUiThread {
                    Toast.makeText(context, "Printer language unknown error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            } finally {
                connection?.close()
            }
        }.start()
    }
}