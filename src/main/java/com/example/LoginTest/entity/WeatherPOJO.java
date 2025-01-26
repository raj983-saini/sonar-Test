package com.example.LoginTest.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class WeatherPOJO {
    public Current current;

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    @Getter
    @Setter
    public class Current{
        public int temperature;
        public List<String> weather_descriptions;
        public int feelslike;

        public int getTemperature() {
            return temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public List<String> getWeather_descriptions() {
            return weather_descriptions;
        }

        public void setWeather_descriptions(List<String> weather_descriptions) {
            this.weather_descriptions = weather_descriptions;
        }

        public int getFeelslike() {
            return feelslike;
        }

        public void setFeelslike(int feelslike) {
            this.feelslike = feelslike;
        }
    }

}
