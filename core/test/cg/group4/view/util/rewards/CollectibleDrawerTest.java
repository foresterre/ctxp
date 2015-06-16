package cg.group4.view.util.rewards;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import cg.group4.data_structures.collection.collectibles.Collectible;
import cg.group4.data_structures.collection.collectibles.FishA;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxNativesLoader;
import cg.group4.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class CollectibleDrawerTest {
	
	/**
	 * The CollectibleDrawer to test.
	 */
	protected CollectibleDrawer cDrawer;
	
	
	@Before
	public void setUp() {
		cDrawer = new CollectibleDrawer();
	}

	@After
	public void tearDown() {
		cDrawer = null;
	}

	@Test
	public void fieldsTest() {
		final float errorMargin = 0.00001f;
		
		final float replaceColourComponent = 0.4705882353f;
		assertEquals(replaceColourComponent, cDrawer.cReplaceColourComponent, errorMargin);
		
		final float alpha = 1f;
		assertEquals(alpha, cDrawer.cAlpha, errorMargin);
		
		final Color replacementColour = new Color(
				replaceColourComponent,
				replaceColourComponent,
				replaceColourComponent,
				alpha);
		assertEquals(replacementColour, cDrawer.cReplacementColour);
	}
	
	@Test
	public void replaceColoursTest1() {
		final Color newColour = new Color(1f, 0f, 0f, 1f);
		Pixmap pix = new Pixmap(new FileHandle(new File("test/testAssets/TestCollectibleDrawer1.png")));
		pix.setColor(newColour);
		cDrawer.replaceColours(pix);
		
		for (int y = 0; y < pix.getHeight(); y++) {
			for (int x = 0; x < pix.getWidth(); x++) {
				Color currentColour = new Color(pix.getPixel(x, y));
				assertEquals(newColour, currentColour);
			}
		}
	}
	
	@Test
	public void replaceColoursTest2() {
		final Color newColour = new Color(1f, 0f, 0f, 1f);
		Pixmap pix = new Pixmap(new FileHandle(new File("test/testAssets/TestCollectibleDrawer2.png")));
		pix.setColor(newColour);
		cDrawer.replaceColours(pix);
		
		for (int y = 0; y < pix.getHeight(); y++) {
			for (int x = 0; x < pix.getWidth(); x++) {
				
				Color currentColour = new Color(pix.getPixel(x, y));
				if (x == y) {
					assertFalse(newColour.equals(currentColour));
				} else {
					assertEquals(newColour, currentColour);
				}
			}
		}
	}
}
