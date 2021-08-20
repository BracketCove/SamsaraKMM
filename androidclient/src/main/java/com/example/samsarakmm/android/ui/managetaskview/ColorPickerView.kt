package com.example.samsarakmm.android.ui.managetaskview



//class ColorPickerView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs),
//    View.OnClickListener {
//    private lateinit var darkBlue: ImageButton
//    private lateinit var burntOrange: ImageButton
//    private lateinit var green: ImageButton
//    private lateinit var red: ImageButton
//    private lateinit var darkLime: ImageButton
//    private lateinit var lightBlue: ImageButton
//    private lateinit var mauve: ImageButton
//    private lateinit var brown: ImageButton
//    private lateinit var teal: ImageButton
//
//    private lateinit var onColorSelected: (COLOR) -> Unit
//
//    override fun onClick(view: View) {
//        val id = view.id
//        when (id) {
//            R.id.imb_dark_blue -> onColorSelected(COLOR.DARK_BLUE)
//            R.id.imb_burnt_orange -> onColorSelected(COLOR.BURNT_ORANGE)
//            R.id.imb_green -> onColorSelected(COLOR.GREEN)
//            R.id.imb_red -> onColorSelected(COLOR.DARK_RED)
//            R.id.imb_dark_lime -> onColorSelected(COLOR.DARK_LIME)
//            R.id.imb_light_blue -> onColorSelected(COLOR.LIGHT_BLUE)
//            R.id.imb_mauve -> onColorSelected(COLOR.MAUVE)
//            R.id.imb_brown -> onColorSelected(COLOR.BROWN)
//            R.id.imb_teal -> onColorSelected(COLOR.TEAL)
//        }
//    }
//
//
//    fun setCallback(onColorSelected: (COLOR) -> Unit) {
//        this.onColorSelected = onColorSelected
//    }
//
//    private fun build() {
//        inflate(context, R.layout.view_color_picker_sheet, this)
//        darkBlue = findViewById(R.id.imb_dark_blue)
//        darkBlue.setOnClickListener(this)
//        burntOrange = findViewById(R.id.imb_burnt_orange)
//        burntOrange.setOnClickListener(this)
//        green = findViewById(R.id.imb_green)
//        green.setOnClickListener(this)
//        red = findViewById(R.id.imb_red)
//        red.setOnClickListener(this)
//        darkLime = findViewById(R.id.imb_dark_lime)
//        darkLime.setOnClickListener(this)
//        lightBlue = findViewById(R.id.imb_light_blue)
//        lightBlue.setOnClickListener(this)
//        mauve = findViewById(R.id.imb_mauve)
//        mauve.setOnClickListener(this)
//        brown = findViewById(R.id.imb_brown)
//        brown.setOnClickListener(this)
//        teal = findViewById(R.id.imb_teal)
//        teal.setOnClickListener(this)
//    }
//
//    init {
//        build()
//    }
//}