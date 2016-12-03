package com.ch.wchhuangya.dzah.android.activity.recyclerview;

import android.os.Bundle;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.enums.AlbumSong;
import com.ch.wchhuangya.dzah.android.vm.RecyclerViewRefreshVM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RefreshActivity extends BaseActivity {

    private RecyclerViewRefreshVM mVm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVm = new RecyclerViewRefreshVM(this, getResources());

        // 初始化 下拉刷新
        mVm.initSwipeRefreshLayout();

        // 初始化 RecyclerView
        mVm.initRecyclerView();

        // 填充数据
        mVm.initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVm.reset();
        mVm.unsubscribe();
    }

    public static List<List<Map<String, Object>>> getJaysAllDatas() {
        List<List<Map<String, Object>>> finalList = new ArrayList<>();

        finalList.add(getJayAlbumList());
        finalList.add(getFantasyAlbumList());
        finalList.add(getEP1List());
        finalList.add(getBDKJAlbumList());
        finalList.add(getYHMAlbumList());
        finalList.add(getEP2List());
        finalList.add(getQLXAlbumList());
        finalList.add(getEP3List());
        finalList.add(getSYYDXBAlbumList());

        return finalList;
    }

    private static List<Map<String, Object>> getJayAlbumList() {
        List<Map<String, Object>> tempList = new ArrayList<>();
        tempList.add(getAlbumInfoMap("2000 年 11 月 7 日", "Jay", 10, R.mipmap.jay));
        tempList.add(getSongInfoMap("可爱女人", "徐若瑄", "周杰伦", "周杰伦", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("完美主义", "方文山", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("星晴", "周杰伦", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("娘子", "方文山", "周杰伦", "周杰伦", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("斗牛", "方文山", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("黑色幽默", "周杰伦", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("伊斯坦堡", "徐若瑄", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("印地安老斑鸠", "方文山", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("龙卷风", "徐若瑄", "周杰伦", "钟兴民", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("反方向的钟", "徐若瑄", "周杰伦", "周杰伦", AlbumSong.SONG_STATE_LISTENED));
        return tempList;
    }

    public static List<Map<String, Object>> getFantasyAlbumList() {
        List<Map<String, Object>> tempList = new ArrayList<>();
        tempList.add(getAlbumInfoMap("2001 年 09 月 20 日", "范特西", 10, R.mipmap.fantasy));
        tempList.add(getSongInfoMap("爱在西元前", "方文山", "周杰伦", "林迈可", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("爸我回来了", "周杰伦", "周杰伦", "钟兴民", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("简单爱", "徐若瑄", "周杰伦", "林迈可", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("忍者", "方文山", "周杰伦", "林迈可", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("开不了口", "徐若瑄", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("上海一九四三", "方文山", "周杰伦", "林迈可", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("对不起", "方文山", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("威廉古堡", "方文山", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("双截棍", "方文山", "周杰伦", "钟兴民", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("安静", "周杰伦", "周杰伦", "钟兴民", AlbumSong.SONG_STATE_SINGED));
        return tempList;
    }

    public static List<Map<String, Object>> getEP1List() {
        List<Map<String, Object>> tempList = new ArrayList<>();
        tempList.add(getAlbumInfoMap("2001 年 12 月 25 日", "Fantasy+Plus(EP)", 3, R.mipmap.fantasyplus));
        tempList.add(getSongInfoMap("蜗牛", "周杰伦", "周杰伦", "吴庆隆", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("你比从前快乐", "方文山", "周杰伦", "涂惠元", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("世界末日", "周杰伦", "周杰伦", "周杰伦", AlbumSong.SONG_STATE_SINGED));
        return tempList;
    }

    public static List<Map<String, Object>> getBDKJAlbumList() {
        List<Map<String, Object>> tempList = new ArrayList<>();
        tempList.add(getAlbumInfoMap("2002 年 07 月 19 日", "八度空间", 10, R.mipmap.bdkj));
        tempList.add(getSongInfoMap("半兽人", "周杰伦", "周杰伦", "林迈可", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("半岛铁盒", "周杰伦", "周杰伦", "林迈可", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("暗号", "许世昌", "周杰伦", "林迈可", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("龙拳", "方文山", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("火车叨位去", "方文山", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("分裂（离开）", "周杰伦", "周杰伦", "钟兴民", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("爷爷泡的茶", "方文山", "周杰伦", "林迈可", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("回到过去", "刘畊宏", "周杰伦", "林迈可", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("米兰的小铁匠", "方文山", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("最后的战役", "方文山", "周杰伦", "钟兴民", AlbumSong.SONG_STATE_SINGED));
        return tempList;
    }

    public static List<Map<String, Object>> getYHMAlbumList() {
        List<Map<String, Object>> tempList = new ArrayList<>();
        tempList.add(getAlbumInfoMap("2003 年 07 月 31 日", "叶惠美", 11, R.mipmap.yhm));
        tempList.add(getSongInfoMap("以父之名", "黄俊郎", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("懦夫", "周杰伦", "周杰伦", "周杰伦", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("晴天", "周杰伦", "周杰伦", "周杰伦", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("三年二班", "方文山", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("东风破", "方文山", "周杰伦", "林迈可", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("你听得到", "曾郁婷", "周杰伦", "林迈可", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("同一种调调", "方文山", "周杰伦", "钟兴民", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("她的睫毛", "方文山", "周杰伦", "周杰伦", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("爱情悬崖", "徐若瑄", "周杰伦", "钟兴民", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("梯田", "周杰伦", "周杰伦", "周杰伦", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("双刀", "方文山", "周杰伦", "钟兴民", AlbumSong.SONG_STATE_LISTENED));
        return tempList;
    }

    public static List<Map<String, Object>> getEP2List() {
        List<Map<String, Object>> tempList = new ArrayList<>();
        tempList.add(getAlbumInfoMap("2003 年 11 月 11 日", "寻找周杰伦EP", 2, R.mipmap.xzzjl));
        tempList.add(getSongInfoMap("轨迹", "黄俊郎", "周杰伦", "钟兴民", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("断了的弦", "方文山", "周杰伦", "钟兴民", AlbumSong.SONG_STATE_SINGED));
        return tempList;
    }

    public static List<Map<String, Object>> getQLXAlbumList() {
        List<Map<String, Object>> tempList = new ArrayList<>();
        tempList.add(getAlbumInfoMap("2004 年 08 月 03 日", "七里香", 10, R.mipmap.qlx));
        tempList.add(getSongInfoMap("我的地盘", "方文山", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("七里香", "方文山", "周杰伦", "钟兴民", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("借口", "周杰伦", "周杰伦", "周杰伦", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("外婆", "周杰伦", "周杰伦", "周杰伦", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("将军", "黄俊郎", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("搁浅", "宋健彰", "周杰伦", "钟兴民", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("乱舞春秋", "方文山", "周杰伦", "钟兴民", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("困兽之斗", "刘畊宏", "周杰伦", "蔡科俊", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("园游会", "方文山", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("止战之殇", "方文山", "周杰伦", "周杰伦", AlbumSong.SONG_STATE_LISTENED));
        return tempList;
    }

    public static List<Map<String, Object>> getEP3List() {
        List<Map<String, Object>> tempList = new ArrayList<>();
        tempList.add(getAlbumInfoMap("2005 年 06 月 26 日", "J III(EP)", 3, R.mipmap.ep3));
        tempList.add(getSongInfoMap("Intro", "无", "无", "无", AlbumSong.SONG_STATE_UNLISTENED));
        tempList.add(getSongInfoMap("飘移", "方文山", "周杰伦", "林迈可", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("一路向北", "方文山", "周杰伦", "蔡科俊", AlbumSong.SONG_STATE_SINGED));
        return tempList;
    }

    public static List<Map<String, Object>> getSYYDXBAlbumList() {
        List<Map<String, Object>> tempList = new ArrayList<>();
        tempList.add(getAlbumInfoMap("2005 年 11 月 01 日", "十一月的肖邦", 12, R.mipmap.syydxb));
        tempList.add(getSongInfoMap("夜曲", "方文山", "周杰伦", "林迈可", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("蓝色风暴", "方文山", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("发如雪", "方文山", "周杰伦", "林迈可", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("黑色毛衣", "周杰伦", "周杰伦", "林迈可", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("四面楚歌", "周杰伦", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("枫", "宋健彰", "周杰伦", "钟兴民", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("浪漫手机", "方文山", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("逆鳞", "黄俊郎", "周杰伦", "钟兴民", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("麦芽糖", "方文山", "周杰伦", "洪敬尧", AlbumSong.SONG_STATE_LISTENED));
        tempList.add(getSongInfoMap("珊瑚海(周杰伦/Lara)", "方文山", "周杰伦", "钟兴民", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("飘移", "方文山", "周杰伦", "林迈可(原声带)", AlbumSong.SONG_STATE_SINGED));
        tempList.add(getSongInfoMap("一路向北", "方文山", "周杰伦", "蔡科俊(原声带)", AlbumSong.SONG_STATE_SINGED));
        return tempList;
    }

    private static Map<String, Object> getAlbumInfoMap(String publishTime, String albumName, int songsCount, int albumPic) {
        Map<String, Object> map = new HashMap<>();
        map.put(AlbumSong.ALBUM_PUBLISH_TIME.getKey(), publishTime);
        map.put(AlbumSong.ALBUM_NAME.getKey(), albumName);
        map.put(AlbumSong.ALBUM_SONGS_COUNT.getKey(), songsCount);
        map.put(AlbumSong.ALBUM_PIC.getKey(), albumPic);
        return map;
    }

    private static Map<String, Object> getSongInfoMap(String songName, String songLyrics, String songTune, String songArrangementm, AlbumSong songState) {
        Map<String, Object> map = new HashMap<>();
        map.put(AlbumSong.SONG_NAME.getKey(), songName);
        map.put(AlbumSong.SONG_LYRICS.getKey(), songLyrics);
        map.put(AlbumSong.SONG_TUNE.getKey(), songTune);
        map.put(AlbumSong.SONG_ARRANGEMENT.getKey(), songArrangementm);
        map.put(AlbumSong.SONG_STATE.getKey(), songState.ordinal());
        return map;
    }
}
