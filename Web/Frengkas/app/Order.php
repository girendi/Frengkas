<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Order extends Model
{
    protected $fillable = [
        'id_user', 'id_pukul', 'id_category', 'location', 'status'
    ];

    public function users(){
        return $this->belongsTo('App\User','id_user');
    }

    public function pukuls(){
        return $this->belongsTo('App\Pukul','id_pukul');
    }

    public function categories(){
        return $this->belongsTo('App\Category','id_category');
    }
}
