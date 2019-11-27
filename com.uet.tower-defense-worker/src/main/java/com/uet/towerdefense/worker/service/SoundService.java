package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import org.springframework.stereotype.Service;

@Service
public class SoundService {

    private MediaPlayer backgroundSound;

    public void init() {
        backgroundSound = AssetUtil.getBackgroundSound();
        backgroundSound.play();
    }

    public void setMuteBackGround(boolean isMute) {
        backgroundSound.setMute(isMute);
    }

    public void soundProduce(String soundName) {
        AudioClip audioClip = AssetUtil.getGameSound(soundName);
        audioClip.play();
    }
}
