package com.endless.enldess_news.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */

public class NewWeather {



    @SerializedName("daily_forecast")
    public List<DailyForecast> forecastList;

}
