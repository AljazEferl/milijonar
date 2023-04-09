package com.example.projektnodelomilijonar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projektnodelomilijonar.databinding.ActivityRegisterBinding
import java.sql.*


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private var conn: Connection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getConnection()
        //executeMySQLQuery()
    }

    private fun getConnection() {
        val url = "jdbc:mysql://sql303.epizy.com:3306/epiz_33963503_milijonar"
        val username = "epiz_33963503"
        val password = "0XYpGgygXZVRG"

        var conn: Connection? = null

        try {
            // Load the JDBC-ODBC bridge driver
            Class.forName("com.mysql.cj.jdbc.Driver")

            // Establish the database connection
            conn = DriverManager.getConnection(url, username, password)

            // Print a message to indicate success
            binding.textView3.text = "Connected to the database."

            // Perform database operations here...
            // For example:
            // val stmt = conn.createStatement()
            // val rs = stmt.executeQuery("SELECT * FROM mytable")
            // while (rs.next()) {
            //     println(rs.getString("column_name"))
            // }

        } catch (ex: ClassNotFoundException) {
            // Handle driver loading errors
            binding.textView3.text = "Error: could not load JDBC-ODBC bridge driver"
            ex.printStackTrace()
        } catch (ex: SQLException) {
            // Handle database connection errors
            binding.textView3.text = "Error: could not connect to the database"
            ex.printStackTrace()
        } finally {
            // Close the database connection (if it was established)
            if (conn != null) {
                try {
                    conn.close()
                    println("Disconnected from the database.")
                } catch (ex: SQLException) {
                    println("Error: could not disconnect from the database")
                    ex.printStackTrace()
                }
            }
        }
    }

    private fun executeMySQLQuery() {
        var stmt: Statement? = null
        var resultset: ResultSet? = null

        try {
            stmt = conn!!.createStatement()
            resultset = stmt!!.executeQuery("SELECT * FROM my_table")

            while (resultset.next()) {
                val id = resultset.getInt("id")
                val name = resultset.getString("name")
                val age = resultset.getInt("age")

                println("id: $id, name: $name, age: $age")
            }
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } finally {
            // release resources
            resultset?.close()
            stmt?.close()
            conn?.close()
        }
    }
}
