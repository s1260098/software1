
public class Main {
	
	// 幅
	private static final int WIDTH = 8;
	// 高さ
	private static final int HEIGHT = 8;
	// WIDHT * HEIGHT
	private static final int WH = WIDTH * HEIGHT;
	
	// 地図
	private boolean[] map = {
			true , false, true , true , true , true , false, true ,
			true , false, true , true , true , true , false, true ,
			true , false, true , true , true , false, false, true ,
			true , false, true , false, true , false, false, true ,
			true , true , true , false, true , true , true , true ,
			true , true , true , true , true , true , false, true ,
			true , false, false, false, false, true , false, false,
			true , true , true , true , true , true , true , true 
	};
	
	// 2点間の距離
	private int[][] dist = new int[WH][WH];
	
	
	// x座標とy座標から通し番号を得る
	private static int getPlaceNum(int x, int y) {
		return x + y * WIDTH;
	}
	
	private void compute() {
		
		// 隣接する場所は距離を1に設定する
		for (int i=0;i<WH;i++) {
			for (int j=i;j<WH;j++) {
				dist[i][j] = WH;
				if (map[i] && map[j]) {
					int offset = j - i;
					switch (offset) {
					case 0:
						dist[i][j] = 0;
						break;
					case 1:
					case -1:
						if (i / WIDTH != j / WIDTH)
							break;
					case WIDTH:
					case -WIDTH:
						dist[i][j] = 1;
						break;
					}
				}
			}
		}
		
		/*
		 * 本体部分
		 * 
		 * 軽量化のために i < j の部分のみ処理する
		 * iからjに移動するとき、kを経由していく場合を考える
		 */
		for (int k=0;k<WH;k++) {
			if (!map[k])
				continue;
			for (int i=0;i<WH;i++) {
				if (i == k)
					continue;
				if (!map[i])
					continue;
				for (int j=i+1;j<WH;j++) {
					if (!map[j])
						continue;
					dist[i][j] = Math.min(dist[i][j], readDist(i, k) + readDist(k, j));
				}
			}
		}
		
		// j <= i の部分を埋める
		for (int i=1;i<WH;i++) {
			for (int j=0;j<i;j++) {
				dist[i][j] = dist[j][i];
			}
		}
	}
	
	// i <= j の順番として読む
	private int readDist(int i, int j) {
		if (i <= j)
			return dist[i][j];
		else
			return dist[j][i];
	}
	
	
	// メイン
	public static void main(String...args) {
		Main main = new Main();
		main.compute();
		for (int y=0;y<8;y++) {
			for (int x=0;x<8;x++) {
				System.out.print("\t" + main.dist[0][getPlaceNum(x, y)]);
			}
			System.out.println();
		}
	}
}