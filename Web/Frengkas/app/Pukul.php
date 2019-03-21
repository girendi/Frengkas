<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Pukul extends Model
{
    protected $fillable = [
        'id_waktu', 'start', 'end', 'status',
    ];

    public function waktus(){
        return $this->belongsTo('App\Waktu','id_waktu');
    }

    public function orders(){
        return $this->hasMany('App\Order','id_pukul');
    }
}
