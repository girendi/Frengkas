<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Category extends Model
{
  protected $fillable = [
      'name', 'price', 'id_service'
  ];

  public function service(){
      return $this->belongsTo('App\Service','id_service');
  }

    public function orders(){
        return $this->hasMany('App\Order','id_category');
    }
}
