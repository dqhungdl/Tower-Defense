package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import org.springframework.stereotype.Service;

@Service
public class SoundService {

    private boolean muteGameSound;
    private MediaPlayer backgroundSound;

    public boolean isMuteGameSound() {
        return muteGameSound;
    }

    public void setMuteGameSound(boolean muteGameSound) {
        this.muteGameSound = muteGameSound;
    }

    public MediaPlayer getBackgroundSound() {
        return backgroundSound;
    }

    public void init() {
        backgroundSound = AssetUtil.getBackgroundSound();
        backgroundSound.setMute(false);
        muteGameSound = false;
        backgroundSound.play();
    }

    public void soundProduce(String soundName) {
        if (muteGameSound)
            return;
        AudioClip audioClip = AssetUtil.getGameSound(soundName);
        audioClip.play();
    }
}
