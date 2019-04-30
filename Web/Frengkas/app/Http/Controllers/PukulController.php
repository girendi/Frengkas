<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Pukul;

class PukulController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index($id_waktu = null)
    {
        $pukuls = Pukul::Where([
                ['id_waktu', '=', $id_waktu],
                ['status', '=', 'free'],
            ])->orderBy('start', 'asc')->get();
        return response()->json(['Pukul' => $pukuls]);
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
        $pukul = new Pukul;
        $pukul->id_waktu = $request->input('id_waktu');
        $pukul->start = $request->input('start');
        $pukul->end = $request->input('end');
        $pukul->status = "free";
        $pukul->save();
        return response()->json($pukul);
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        return Pukul::find($id);
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
        $pukul = Pukul::find($id);
        $pukul->start = $request->input('start');
        $pukul->end = $request->input('end');
        $pukul->status = $request->input('status');
        $pukul->save();
        return response()->json($pukul);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $pukul = Pukul::find($id)->delete();
        return 'Pukul successfully deleted';
    }
}
