<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Waktu extends Model
{
    protected $fillable = [
        'date',
    ];

    public function pukuls(){
        return $this->hasMany('App\Pukul','id_waktu');
    }
}
