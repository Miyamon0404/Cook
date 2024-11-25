package cook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class CookUI {
	
	IngredientDatabase ingredientDatabase;
	MenuMatcher menumatcher;
//	RecipeDatabase racipedatabase;
	List<Menu> menus;//Recipedatabaseの代用。メニューのリストを参照。
	
	// テスト用のメインクラス
	public static void main(String[] args) {
        // 現在の日付を設定
        String todayDate = "2024/11/18"; // YYYY/MM/DD 形式

        // 食材リストを作成
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(new Ingredient("Tomato", "2024/11/20", 150, 1.4, 0.3, 6.0));
        ingredientList.add(new Ingredient("Chicken Breast", "2024/11/22", 150, 31.0, 3.6, 0.0));
        ingredientList.add(new Ingredient("Rice", "2025/06/01", 500, 7.1, 0.6, 77.0));
        ingredientList.add(new Ingredient("Onion", "2024/11/19", 150, 1.1, 0.1, 9.3));

        // 食材データベースを作成
        IngredientDatabase ingredientDatabase = new IngredientDatabase(ingredientList);

        // メニューリストを作成
        Map<String, Double> menu1Ingredients = new HashMap<>();
        menu1Ingredients.put("Tomato", 2.0);
        menu1Ingredients.put("Onion", 1.0);
        menu1Ingredients.put("Rice", 1.0);

        Map<String, Double> menu2Ingredients = new HashMap<>();
        menu2Ingredients.put("Chicken Breast", 1.0);
        menu2Ingredients.put("Rice", 2.0);

        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu("Tomato Rice", menu1Ingredients));
        menus.add(new Menu("Chicken Rice", menu2Ingredients));
        
        //UIの起動
        CookUI cookUI = new CookUI();
        cookUI.setIngredientDatabase(ingredientDatabase);
        cookUI.setMenus(menus);
        cookUI.StartMenu();
	}
	
	public CookUI() {
	}
	
//	public CookUI(IngredientDatabase ingredientDatabase, RecipeDatabase racipedatabase) {
//		this.ingredientDatabase = ingredientDatabase;
//		this.menumatcher = new MenuMatcher(ingredientDatabase, 0, 0, 0);
//	}
	
	//レシピデータベースのセッタ
//	public setRecipeDatabase(RecipeDatabase racipedatabase) {
//		this.RecipeDatabase racipedatabase = ingredientDatabase;
//}
	
	//材料データベースのセッタ
	public void setIngredientDatabase(IngredientDatabase ingredientDatabase) {
		this.ingredientDatabase = ingredientDatabase;
	}
	
	//メニューデータベースのセッタ
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
	//StartMenuの表示
	public void StartMenu() {
		boolean endflag = false;
		while(endflag == false) {
			System.out.println("*****StartMenu:*****");
			System.out.println("--材料のリスト--");
			displayIngredient();
			System.out.println("---操作一覧---");
			System.out.println("1.材料を追加する");			
			System.out.println("2.最適なメニューを探索する");			
			System.out.println("3.プログラムの終了");
			System.out.println("-------------");
			System.out.println("実行したい操作の番号を入力してください。");
			Scanner scan = new Scanner(System.in);
			int x = scan.nextInt();
			switch(x) {
			case 1: inputIngreadient(); break;
			case 2: searchOptimalMenu(); break;
			case 3: endflag = true; System.out.println("プログラムを終了します。"); break;
			}
		}
	}
	
	//現存する素材を全て表示
	public void displayIngredient() {
		ingredientDatabase.displayIngredients();
	}
	
	//最適なメニューを探索する
	public void searchOptimalMenu() {
		System.out.println("目標とする栄養素を入力してください");
		
		this.menumatcher = new MenuMatcher(ingredientDatabase, 0, 0, 0);
		menumatcher.findOptimalMenuBFS(menus);
	}
	
	//素材の内容を入力
	public void inputIngreadient() {
		Scanner scan = new Scanner(System.in);
		System.out.println("材料の情報を入力してください");
		System.out.println("名前(xxxx):");
		String name = scan.next();
		System.out.println("賞味期限(YYYY/MM/DD):");
		String expiryDate = scan.next();   	// 賞味期限
		System.out.println("食材の量(g):");
		double quantity = scan.nextDouble();     	// 食材の量
		System.out.println("タンパク質の量(g):");
		double protein = scan.nextDouble();      	// タンパク質の量
		System.out.println("脂質の量(g):");
		double lipids = scan.nextDouble();		 	// 脂質の量
		System.out.println("炭水化物の量(g):");
	    double carbohydrates= scan.nextDouble();	// 炭水化物の量
	    var ingredient = new Ingredient(name, expiryDate, quantity, protein, lipids, carbohydrates);
	    System.out.println(ingredient + "を材料リストに追加しました");
		ingredientDatabase.addIngredient(ingredient);
	}
	
}