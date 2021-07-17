package com.ciembro.healthappfront.extrasuper.service;

import com.ciembro.healthappfront.extrasuper.dto.CreatedInsightsDto;
import com.ciembro.healthappfront.extrasuper.dto.EmotionalStateDto;
import com.ciembro.healthappfront.extrasuper.dto.SideEffectDto;
import com.ciembro.healthappfront.extrasuper.dto.WeatherConditionsDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class InsightsService {

    public List<EmotionalStateDto> getEmotions(){
        List<EmotionalStateDto> emotions = new ArrayList<>();
        emotions.add(new EmotionalStateDto(1L, "JOY", "Radość"));
        emotions.add(new EmotionalStateDto(2L, "SADNESS", "Smutek"));
        emotions.add(new EmotionalStateDto(3L, "ANGER", "Złość"));
        emotions.add(new EmotionalStateDto(4L, "CALM", "Spokój"));
        emotions.add(new EmotionalStateDto(5L, "FEAR", "Lęk"));
        emotions.add(new EmotionalStateDto(1L, "JOY", "Radość"));
        emotions.add(new EmotionalStateDto(2L, "SADNESS", "Smutek"));
        emotions.add(new EmotionalStateDto(3L, "ANGER", "Złość"));
        emotions.add(new EmotionalStateDto(4L, "CALM", "Spokój"));
        emotions.add(new EmotionalStateDto(5L, "FEAR", "Lęk"));
        return emotions;
    }

    public List<SideEffectDto> getSideEffects(){
        List<SideEffectDto> sideEffects = new ArrayList<>();
        sideEffects.add(new SideEffectDto(1L, "Ból głowy"));
        sideEffects.add(new SideEffectDto(2L, "Ból pleców"));
        sideEffects.add(new SideEffectDto(3L, "Zawroty głowy"));
        sideEffects.add(new SideEffectDto(4L, "Senność"));
        sideEffects.add(new SideEffectDto(5L, "Beczka śmiechu"));
        return sideEffects;
    }

    public List<CreatedInsightsDto> getInsights(){
        CreatedInsightsDto createdInsightsDto = new CreatedInsightsDto();
        createdInsightsDto.setId(1L);
        createdInsightsDto.setCreationDate(LocalDate.now());
        createdInsightsDto.setComment("this is extra comment");
        createdInsightsDto.setUsername("user1");
        WeatherConditionsDto w =  new WeatherConditionsDto();
        w.setIconUrl("//cdn.weatherapi.com/weather/64x64/night/176.png");
        createdInsightsDto.setWeather(w);
        createdInsightsDto.setEmotions(new HashSet<>(getEmotions()));
        createdInsightsDto.setSideEffects(new HashSet<>(getSideEffects()));

        CreatedInsightsDto createdInsightsDto1 = new CreatedInsightsDto();
        createdInsightsDto1.setId(1L);
        createdInsightsDto1.setCreationDate(LocalDate.now());
        createdInsightsDto1.setComment("this is extra comment");
        createdInsightsDto1.setUsername("user2");
        WeatherConditionsDto w1 =  new WeatherConditionsDto();
        w1.setIconUrl("//cdn.weatherapi.com/weather/64x64/night/176.png");
        createdInsightsDto1.setWeather(w1);
        createdInsightsDto1.setEmotions(new HashSet<>(getEmotions()));
        createdInsightsDto1.setSideEffects(new HashSet<>(getSideEffects()));

        CreatedInsightsDto createdInsightsDto2 = new CreatedInsightsDto();
        createdInsightsDto2.setId(1L);
        createdInsightsDto2.setCreationDate(LocalDate.now());
        createdInsightsDto2.setComment("this is extra comment");
        createdInsightsDto2.setUsername("user3");
        WeatherConditionsDto w2 =  new WeatherConditionsDto();
        w2.setIconUrl("//cdn.weatherapi.com/weather/64x64/night/176.png");
        createdInsightsDto2.setWeather(w2);
        createdInsightsDto2.setEmotions(new HashSet<>(getEmotions()));
        createdInsightsDto2.setSideEffects(new HashSet<>(getSideEffects()));

        return Arrays.asList(createdInsightsDto, createdInsightsDto1,createdInsightsDto2);
    }


}
