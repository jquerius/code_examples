class ExampleFragment : Fragment() {
    
    // this is the instance of our parent activity's interface that we define here 
    private var mListener: OnFragmentInteractionListener? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    
    // We use this function to call onFragmentInteraction of our parent class 
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }
    
    override fun onDetach() {
        super.onDetach()
        mListener = null
    }
    
    /**
     * Here we define the methods that we can fire off 
     * in our parent Activity once something has changed
     * within the fragment. 
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
}

class ParentActivity : ParentActivity.OnFragmentInteractionListener, AppCompatActivity() {
    // this is the callback-like function that will run when the fragment 
    // tells it to 
    override fun onFragmentInteraction(uri: Uri) {
        // save some data from the fragment...
        // other business logic...
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}