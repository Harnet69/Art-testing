# Hilt - dependency injection JetPack library
Implementation: Art testing
1. Add Hilt dependencies to Gradle
    kapt "com.google.dagger:hilt-android-compiler:2.31.2-alpha"
    kaptAndroidTest 'com.google.dagger:hilt-android-compiler:2.31.2-alpha'
    androidTestImplementation 'com.google.dagger:hilt-android-testing:2.28-alpha'
    
1.1. Add to MainActivity class annotation: @AndroidEntryPoint
	to let Hilt know where the application starts

2. Create class AppNameApplication with Hilt annotation:
    @HiltAndroidApp
    class ArtTestingApp: Application()
    
3. Add to manifest file:
    android:name=".AppNameApplication"
    
4. Create provider functions(when we create an object Hilt provides them whenever we need them):
  - create an class - object AppModule
  	@Module
	// provide LifeCircle for provider functions
	@InstallIn(SingletonComponent::class)
	object AppModule {
		@Singleton
    		@Provides
    		fun injectRoomDatabase(@ApplicationContext context: Context) =
        	Room.databaseBuilder(context, ArtDatabase::class.java, "ArtDB").build()
        	
      	        @Singleton
    		@Provides
    		fun injectDao(database: ArtDatabase) = database.artDao()

    		@Singleton
    		@Provides
    		fun injectRetrofitAPI(): RetrofitAPI{
        	 return Retrofit.Builder()
            	.addConverterFactory(GsonConverterFactory.create())
            	.baseUrl(BASE_URL)
            	.build()
            	.create(RetrofitAPI::class.java)
    		}
	}
  
5. Inject dependencies to a repository to use its function from AppModule:
	@Inject constructor(private val artDao: ArtDao, private val retrofitAPI: RetrofitAPI)
