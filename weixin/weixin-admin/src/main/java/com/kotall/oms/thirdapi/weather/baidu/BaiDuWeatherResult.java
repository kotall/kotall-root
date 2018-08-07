package com.kotall.oms.thirdapi.weather.baidu;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/5/2
 */
@Data
public class BaiDuWeatherResult implements Serializable {
    private String date;
    private String status;
    private String error;

    private List<Weather> results;

    @Data
    public class Weather {
        private String currentCity;
        private String pm25;
        private List<Info> index;

        @SerializedName("weather_data")
        private List<WeatherData> weatherData;

        @Data
        public class WeatherData {
            private String date;
            private String dayPictureUrl;
            private String nightPictureUrl;
            private String temperature;
            private String weather;
            private String wind;

        }
        @Data
        public class Info {
            private String des;
            private String tipt;
            private String title;
            private String zs;
        }
    }


}
