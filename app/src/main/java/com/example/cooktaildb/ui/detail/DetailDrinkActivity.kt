package com.example.cooktaildb.ui.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.cooktaildb.Constant
import com.example.cooktaildb.R
import com.example.cooktaildb.base.BaseActivity
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.repository.DrinkRepository
import com.example.cooktaildb.data.source.local.DatabaseHelper
import com.example.cooktaildb.data.source.local.LocalDrinkDataSource
import com.example.cooktaildb.data.source.local.dao.FavoriteDrinkDAOImpl
import com.example.cooktaildb.data.source.remote.RemoteDrinkDataSource
import com.example.cooktaildb.databinding.ActivityDetailDrinkBinding

class DetailDrinkActivity :
    BaseActivity<ActivityDetailDrinkBinding>(ActivityDetailDrinkBinding::inflate),
    DetailDrinkActivityContract.View,
    View.OnClickListener {

    private var detailDrinkActivityPresenter: DetailDrinkActivityPresenter? = null
    private var mYoutubeLink: Uri? = null
    private var mImageResourceLink: Uri? = null
    private var drink: Drink? = null
    private var isFavorite: Boolean? = null

    override fun initData() {
        detailDrinkActivityPresenter = DetailDrinkActivityPresenter(
            DrinkRepository.getInstance(
                RemoteDrinkDataSource.getInstance(),
                LocalDrinkDataSource.getInstance(
                    FavoriteDrinkDAOImpl.getInstance(DatabaseHelper.getInstance(this))
                )
            ),
            this
        )

        val bundle = intent.extras
        val idDrink = bundle?.getString(Constant.BUNDLE_ID_DRINK)
        if (idDrink != null) {
            detailDrinkActivityPresenter?.getDrinkByID(idDrink)
        } else {
            detailDrinkActivityPresenter?.getRandomDrink()
        }
        idDrink?.let { detailDrinkActivityPresenter?.isFavorite(it) }
        binding?.apply {
            buttonYoutube.setOnClickListener(this@DetailDrinkActivity)
            buttonImageSource.setOnClickListener(this@DetailDrinkActivity)
            buttonBackDetail.setOnClickListener(this@DetailDrinkActivity)
            buttonFavoriteDetail.setOnClickListener(this@DetailDrinkActivity)
        }
    }

    override fun getDrinkByIDSuccess(drinks: List<Drink>) {
        drink = drinks.first()
        bindData(drinks[0])
    }

    override fun getRandomDrinkSuccess(drinks: List<Drink>) {
        drink = drinks.first()
        bindData(drinks[0])
    }

    override fun insertDrinkSuccess() {
        Toast.makeText(this, R.string.msg_add_drink_to_favorite, Toast.LENGTH_SHORT).show()
    }

    override fun deleteDrinkSuccess() {
        Toast.makeText(this, R.string.msg_delete_drink_from_favorite, Toast.LENGTH_SHORT).show()
    }

    override fun isFavorite(result: Boolean) {
        isFavorite = result
        if (result) {
            binding?.buttonFavoriteDetail?.setImageResource(R.drawable.ic_star_detail)
            drink?.idDrink?.let { detailDrinkActivityPresenter?.deleteDrink(it) }
        } else {
            binding?.buttonFavoriteDetail?.setImageResource(R.drawable.ic_star_yellow)
            drink?.let { detailDrinkActivityPresenter?.insertDrink(it) }
        }
    }

    override fun showProgressBar() {
        binding?.progressDetail?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding?.progressDetail?.visibility = View.GONE
    }

    override fun showError() {
        Toast.makeText(this, R.string.msg_get_data_failed, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button_youtube -> {
                Intent(Intent.ACTION_VIEW, mYoutubeLink).also {
                    startActivity(it)
                }
            }
            R.id.button_image_source -> {
                Intent(Intent.ACTION_VIEW, mImageResourceLink).also {
                    startActivity(it)
                }
            }
            R.id.button_back_detail -> finish()
            R.id.button_favorite_detail -> {
                Toast.makeText(this, "ab", Toast.LENGTH_SHORT).show()
                binding?.buttonFavoriteDetail?.apply {
                    setImageResource(if (isFavorite == true) R.drawable.ic_star_detail else R.drawable.ic_star_yellow)
                }
            }
        }
    }

    private fun bindData(drink: Drink) {
        binding?.apply {
            val strIngredients = StringBuilder()
            drink.ingredients.forEach {
                if (it != Constant.NULL) {
                    strIngredients.appendLine(it)
                }
            }
            val strMeasures = StringBuilder()
            drink.measures.forEach {
                if (it != Constant.NULL) {
                    strMeasures.appendLine(it)
                }
            }
            Glide.with(this@DetailDrinkActivity).load(drink.strDrinkThumb).into(imageDrinkDetail)
            textDrinkName.text = drink.strDrink
            textDrinkCategory.text = drink.strCategory
            textInstruction.text = drink.strInstructions
            textIngredient.text = strIngredients
            textMeasures.text = strMeasures
            if (drink.strVideo != null) {
                mYoutubeLink = Uri.parse(drink.strVideo)
            } else {
                buttonYoutube.visibility = View.GONE
            }
            if (drink.strImageSource != null) {
                mImageResourceLink = Uri.parse(drink.strImageSource)
            } else {
                buttonImageSource.visibility = View.GONE
            }
        }
    }

    companion object {
        fun getIntent(startFragment: Context) =
            Intent(startFragment, DetailDrinkActivity::class.java)
    }
}
