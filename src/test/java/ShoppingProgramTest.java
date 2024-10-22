
import com.mycompany.assignment2.Controllers.BrowseController;
import com.mycompany.assignment2.Controllers.MasterController;
import Database.DatabaseManager;
import com.mycompany.assignment2.MainFrame;
import com.mycompany.assignment2.Product.CartItem;
import com.mycompany.assignment2.Product.PhoneProduct;
import com.mycompany.assignment2.Product.Product;
import com.mycompany.assignment2.Views.BrowsePanel;
import com.mycompany.assignment2.Views.HomePanel;
import com.mycompany.assignment2.Views.ProductInfoPanel;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author flynn
 */
public class ShoppingProgramTest {
    DatabaseManager model;
    public ShoppingProgramTest() {
        
    }
    
    @Before
    public void setUp() {
        model = DatabaseManager.getInstance();
    }
    
    @Test
    public void testPurchaseFlow() { //Test purchase flow: login, add to cart, checkout, purchase history
        int testingProductId = 100;
        
        model.userLoginSignup("flynn");
        assertEquals("flynn", model.getActiveUserName());
        int previousPurchaseCount = model.getPurchases().get(testingProductId);
        //Clear cart from any previous interactions or testing
        model.clearCart();
        
        //Adding product to cart
        model.addToCart(testingProductId);
        assertEquals(1, model.getCart().size());
        
        //Checkout
        model.savePurchases();
        model.clearCart();
        
        //Purchased item in purchase history
        int currentCount = model.getPurchases().get(testingProductId);
        assertEquals(previousPurchaseCount+1, currentCount);
        
    }
    
    @Test
    public void testAddRemoveCart() { //Test adding products with multiple quantity to cart then removing
        
        model.userLoginSignup("user123");
        assertEquals("user123", model.getActiveUserName());
        model.clearCart();
        
        //Adding products to cart
        model.addToCart(200);
        model.addToCart(101);
        model.addToCart(200);
        
        //Look through cart for product 200, quantity should be 2
        for(CartItem item : model.getCart()) {
            if(item.getProduct().getSku() == 200) {
                assertEquals(2, item.getQuantity());
                break;
            }
        }        
        //Remove one quantity from cart
        model.removeFromCart(200);
        
        //Check quantity of cart item is now 1
        for(CartItem item : model.getCart()) {
            if(item.getProduct().getSku() == 200) {
                assertEquals(1, item.getQuantity());
                break;
            }
        }
    }
    
    @Test
    public void testBrowsePanelDisplaying() { //Test gui displaying products to browse
        BrowsePanel view = new BrowsePanel(new ProductInfoPanel());
        BrowseController controller = new BrowseController(new MasterController(new MainFrame()), view);
        
        ArrayList<Product> prodsTest = new ArrayList();
        prodsTest.add(new PhoneProduct(110, "Test Phone XL", 999.99, "blue"));
        prodsTest.add(new PhoneProduct(111, "Test Phone Plus", 799.99, "black"));
        prodsTest.add(new PhoneProduct(112, "Test Phone Max", 1099.29, "red"));
        
        controller.setBrowseProducts(prodsTest);
        controller.refreshPanel();
        assertEquals(3, view.getProductButtons().size());
    }
    
    @Test
    public void testActiveUserChangeDataUpdates() {
        //Initial user login
        model.userLoginSignup("user100");
        //Add to cart
        model.addToCart(201);
        
        //Simulate changing acocunt to another user
        model.userLoginSignup("user200");
        //Check cart updated
        assertEquals(0, model.getCart().size());
        
        //Reset cart states for both users for future test runs
        model.clearCart();
        model.userLoginSignup("user100");
        model.clearCart();
        
    }
    
    @Test
    public void testUserLoginGreeting() { //Test if user can login, then receives greeting on home page - indicating successful login
        MainFrame frame = new MainFrame();
        MasterController master = new MasterController(frame);

        //Simulator login button press
        model.userLoginSignup("DemoUsername");
        master.setActiveUserGreeting();

        //Check if greeting updated
        HomePanel view = (HomePanel) frame.getPanel("Home");
        assertEquals("Welcome, DemoUsername", view.getGreetingText());
    }
}
