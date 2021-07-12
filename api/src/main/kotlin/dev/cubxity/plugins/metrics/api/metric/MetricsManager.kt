/*
 *     This file is part of UnifiedMetrics.
 *
 *     UnifiedMetrics is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     UnifiedMetrics is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with UnifiedMetrics.  If not, see <https://www.gnu.org/licenses/>.
 */

package dev.cubxity.plugins.metrics.api.metric

import dev.cubxity.plugins.metrics.api.metric.collector.MetricCollection
import dev.cubxity.plugins.metrics.api.metric.collector.collect
import dev.cubxity.plugins.metrics.api.metric.data.Metric

interface MetricsManager {
    val collections: List<MetricCollection>

    fun initialize()

    fun registerCollection(collection: MetricCollection)

    fun unregisterCollection(collection: MetricCollection)

    fun registerDriver(name: String, factory: MetricsDriverFactory<out Any>)

    fun dispose()
}

fun MetricsManager.collect(): List<Metric> =
    collections.flatMap { it.collect() }