import com.kmschr.brs.SaveData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SaveDataTest {

    SaveData saveData;

    @Test
    void testMap() {
        saveData = new SaveData();
        assertEquals("Plate", saveData.getMap());
        assertThrows(IllegalArgumentException.class, () -> saveData.setMap("Slopes"));
        saveData.setMap("Peaks");
        assertEquals("Peaks", saveData.getMap());
    }

    @Test
    void testAuthor() {
        saveData = new SaveData();
        assertEquals("Smallguy", saveData.getAuthor().getName());
    }

}
