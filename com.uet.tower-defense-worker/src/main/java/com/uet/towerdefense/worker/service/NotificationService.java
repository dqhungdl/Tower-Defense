package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.enums.RenderLevels;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NodeService nodeService;

    private Text notification;

    public Text getNotification() {
        return notification;
    }

    public void setNotification(Text notification) {
        this.notification = notification;
    }

    public void init() {
        ImageView imageView = new ImageView(AssetUtil.getButtonImage("6", GamePlays.ADDED_WIDTH, 170));
        imageView.setX(GamePlays.WIDTH * GamePlays.SPRITE_SIZE);
        imageView.setY(330);
        imageView.setId(RenderLevels.MENU);
        nodeService.add(imageView);
        notification = new Text();
        notification.setX(GamePlays.WIDTH * GamePlays.SPRITE_SIZE+15);
        notification.setY(380);
        notification.setFill(Color.BLACK);
        notification.setId(RenderLevels.TEXT);
        notification.setFont(Font.font(GamePlays.FONT, FontWeight.BOLD, 25));
        notification.setLineSpacing(GamePlays.ADDED_WIDTH);
        nodeService.add(notification);
    }

    public void setNotification(String str) {
        notification.setText(str);
    }
}
