package timothypaetz.com.recyclersectionheader

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timothypaetz.com.recyclersectionheader.RecyclerSectionItemDecoration.SectionCallback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            true
        )
        val presidents = ChatDateRepo().chatList
        val sectionItemDecoration = RecyclerSectionItemDecoration(
            resources.getDimensionPixelSize(R.dimen.recycler_section_header_height),
            true,
            getSectionCallback(presidents)
        )
        recyclerView.addItemDecoration(sectionItemDecoration)
        recyclerView.adapter = PersonAdapter(
            layoutInflater,
            presidents,
            R.layout.recycler_row
        )
    }

    private fun getSectionCallback(people: List<ChatListResp>): SectionCallback {
        return object : SectionCallback {
            override fun isSection(position: Int): Boolean {
                var getCurrentPositionData = people[position].header_time
                var getPreviousData =""
                if(position>0){
                    getPreviousData= people[position-1].header_time!!
                    Log.e("PreviousData","is section Previousdata : ${people[position-1].message}   ${people[position-1].header_time.toString()}")
                }
                return (position == 0 ||  getCurrentPositionData != getPreviousData)
            }

            override fun getSectionHeader(position: Int): String {
                var getHeaderTitele   =""
                if(!people[position].header_time!!.equals("")){
                    getHeaderTitele=people[position].header_time!!
                }

                return getHeaderTitele.toString()
            }
        }
    }
}