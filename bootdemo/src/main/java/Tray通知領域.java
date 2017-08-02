
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * タスクトレイ常駐アプリサンプル
 */
public class Tray通知領域 {

	private static final String STR_BOWU_URL = "http://blog.eastmoney.com/touzishimalasong/bloglist_0_1.html";

	/** コンストラクタ */
	public Tray通知領域() throws IOException, AWTException {

		// タスクトレイアイコン
		Image image = ImageIO.read(getClass().getResourceAsStream("1.png"));
		final TrayIcon icon = new TrayIcon(image);
		icon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				icon.displayMessage("アイコンクリック", "アイコンがダブルクリックされました", MessageType.WARNING);
			}
		});

		// ポップアップメニュー
		PopupMenu menu = new PopupMenu();
		// メニューの例
		MenuItem aItem = new MenuItem("メニューの例");
		aItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				icon.displayMessage("メニューの例", "メニューが選択されました", MessageType.ERROR);
			}
		});
		// 終了メニュー
		MenuItem exitItem = new MenuItem("終了");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// メニューにメニューアイテムを追加
		menu.add(aItem);
		menu.add(exitItem);
		icon.setPopupMenu(menu);

		// タスクトレイに格納
		SystemTray.getSystemTray().add(icon);

		icon.displayMessage("テスト", "テストです。", MessageType.INFO);

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				icon.displayMessage("タイマー", "タイマータスクです。", MessageType.INFO);
			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 1 * 60 * 1000); // 1分ごと
	}

	public String getBoWuInfo(String url) {
		String strRet = "";
		// try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

		// try-with-resources
		try (CloseableHttpClient httpClient = HttpClients.custom().build()) {

			HttpGet getMethod = new HttpGet(url);

			try (CloseableHttpResponse response = httpClient.execute(getMethod)) {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

					HttpEntity entity = response.getEntity();
					String body = EntityUtils.toString(entity, StandardCharsets.UTF_8);
					System.out.println(body);
					EntityUtils.consume(entity);
				}
			}
		} catch (IOException e) {

		}

		return strRet;
	}

	/** メインメソッド */
	public static void main(String[] args) throws Exception {
		Tray通知領域 taskTray = new Tray通知領域();
		taskTray.getBoWuInfo(STR_BOWU_URL);
	}
}