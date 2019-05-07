package com.portugal.andrii.kursprivat1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.telecom.Call
import android.widget.LinearLayout
import android.widget.Toast
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.recyclerView1)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val users = ArrayList<Kurs>()
//        users.add(Kurs("Paul", "Mr","aaa1","aaa2"))
//        users.add(Kurs("Jane", "Miss","bbb1","bbb2"))
//        users.add(Kurs("John", "Dr","ccc1","ccc2"))
//        users.add(Kurs("Amy", "Mrs","ddd1","ddd2"))
        val bankService = BankService.create().getKurs(5)
        bankService.enqueue(object : retrofit2.Callback<List<Kurs>> {
            override fun onFailure(call: retrofit2.Call<List<Kurs>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"ERROR",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: retrofit2.Call<List<Kurs>>, response: Response<List<Kurs>>) {
                println(response.body())
                users.addAll(response.body()!!)
                var adapter:CustomAdapter=CustomAdapter(users)
                rv.adapter = adapter
                adapter = CustomAdapter(users)
            }

        })


    }
}
