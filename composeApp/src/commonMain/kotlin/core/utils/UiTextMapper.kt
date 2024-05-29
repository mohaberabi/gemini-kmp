package core.utils

import DataError

fun DataError.asUiText(): UiText {


    return UiText.DynamicString(this.toString())
//    return when (this) {
////        DataError.Local.DISK_FULL -> core.utils.UiText.StringRes(Res.string)
////        DataError.Network.NO_NETWORK -> core.utils.UiText.StringRes(R.string.no_netowrk)
////        DataError.Network.CONFLICT -> core.utils.UiText.StringRes(R.string.conflict)
////        DataError.Network.SERIALIZATION_ERROR -> core.utils.UiText.StringRes(R.string.serialize_error)
////        DataError.Network.UNKNOWN_ERROR -> core.utils.UiText.StringRes(R.string.unknown_error)
////        DataError.Network.PAYLOAD_TOO_LARGE -> core.utils.UiText.StringRes(R.string.payload_too_large)
////        DataError.Network.REQUEST_TIMEOUT -> core.utils.UiText.StringRes(R.string.request_timeout)
////        DataError.Network.TOO_MANY_REQUEST -> core.utils.UiText.StringRes(R.string.too_many_request)
////        DataError.Network.SERVER_ERROR -> core.utils.UiText.StringRes(R.string.server_error)
////        DataError.Network.UNAUTHORIZED -> core.utils.UiText.StringRes(R.string.unAuthed)
////        else -> core.utils.UiText.StringRes(R.string.unknown_error)
//    }
}