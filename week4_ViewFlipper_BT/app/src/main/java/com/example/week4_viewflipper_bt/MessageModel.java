package com.example.week4_viewflipper_bt;
import java.io.Serializable;
import java.util.List;

public class MessageModel implements Serializable {
    private boolean success;
    private String message;
    private List<ImagesSlider> result;

    public boolean isSuccess() {
        return success;
    }

    public List<ImagesSlider> getResult() {
        return result;
    }
}