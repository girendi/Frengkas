<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreatePukulTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('pukuls', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('id_waktu')->unsigned();
            $table->string('start');
            $table->string('end');
            $table->string('status');
            $table->timestamps();

            $table->foreign('id_waktu')->references('id')->on('waktus');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('pukuls');
    }
}
