<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Waktu;
use Carbon\Carbon;

class WaktuController extends Controller
{

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {

        $mytime = Carbon::now()->format('d M Y');

        $waktus = Waktu::orderBy('date', 'asc')->get();
        return response()->json(['Waktu' => $waktus]);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $waktu = New Waktu;
        $waktu->date = $request->input('date');
        $waktu->save();
        return response()->json($waktu);
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        return Waktu::find($id);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $waktu = Waktu::find($id);
        $waktu->date = $request->input('date');
        $waktu->save();
        return response()->json($waktu);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $waktu = Waktu::find($id)->delete();
        return 'Waktu successfully deleted';
    }
}
