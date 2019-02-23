package test

import com.lubulwa.reqrez.schedulers.SchedulerProvider
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TestImmediateSchedulerProvider @Inject constructor() : SchedulerProvider() {
  override fun io() = Schedulers.trampoline()
  override fun ui() = Schedulers.trampoline()
}
