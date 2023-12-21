
import com.example.notesapp.data.model.FoodModel

interface GetAllFoodsRepository {
    suspend fun getAllFoods(): List<FoodModel>
}